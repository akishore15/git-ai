from flask import Flask, request, jsonify

app = Flask(__name__)

@app.route('/chat', methods=['POST'])
def chat():
    user_message = request.json.get('message')
    response_message = generate_response(user_message)
    return jsonify({"response": response_message})

def generate_response(message):
    # Simple rule-based response for demonstration
    return f"You said: {message}. This is a simple AI response."

if __name__ == '__main__':
    app.run(debug=True)
