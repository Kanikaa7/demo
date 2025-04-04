from flask import Flask, request, jsonify
from smolagents import Tool, tool, OpenAIServerModel, CodeAgent
import os
from dotenv import load_dotenv

# Load environment variables from .env file
load_dotenv()

# Now access the key like this
api_key = os.getenv("GEMINI_API_KEY")

class TransactionQueryTool(Tool):
    name = "object_query_tool"
    description = """
    Allows you to apply if condition queries on the data objects. Returns a string representation of the result.
    """

    inputs = {
        "user_statement": {
            "type": 'boolean',
            "description": "Enter a valid if condition.",
        }
    }
    output_type = "string"

    def __init__(self):
        super().__init__()
        self.data = []  # Store received data here

    def set_data(self, data):
        """Store data received from the API"""
        self.data = data

    def forward(self, user_statement: bool):
        """Filter transactions based on the user statement."""
        filtered_transactions = self.filter_transactions(bool(user_statement))

        if not filtered_transactions:
            return "No transactions found matching your criteria."

        return "\n".join(str(t) for t in filtered_transactions)

    def filter_transactions(self, statement: bool):
        """Apply filtering on the stored transactions."""
        if not self.data:
            return []

        return [t for t in self.data if statement]  # Replace with actual filtering logic


# Step 4: Create an instance of the tool
object_query_tool = TransactionQueryTool()

# Specify the model, which will leverage the api_key we just got
model=OpenAIServerModel(
    model_id="gemini-2.0-flash-exp",
    api_base="https://generativelanguage.googleapis.com/v1beta/openai/",
    api_key=api_key,
    temperature=0.7
)

agent = CodeAgent(
    model=model,
    tools=[object_query_tool]
)

def answer_question(question: str) -> str:
    print(f"Input question: {question}, Type: {type(question)}")  # Print the input and its type
    return agent.run(f"""
      You are a booking agent for a fraud detection company. Your goal is to flag fruad accounts on return orders. Your personality is polite and friendly.
      If there is any account customer wants to flag something from allow, deny or review, you will do that for it.

      Answer the following customer inquiry:
      '{question}'
    """)

app = Flask(__name__)

@app.route('/process-data', methods=['POST'])
def process_data():
    data = request.get_json()  # Receive data from Spring Boot
    if not data:
        return jsonify({"error": "No data received"}), 400

    # Store data in the tool instance
    object_query_tool.set_data(data)

    print("Received and stored data:", object_query_tool.data)

    response_from_ai = answer_question("Check if any email has more than 5 return orders")

    return jsonify({"ai_response": response_from_ai})


if __name__ == '__main__':
    app.run(port=5000, debug=True)