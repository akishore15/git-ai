document.addEventListener("DOMContentLoaded", () => {
    const userInput = document.getElementById("userInput");
    const sendBtn = document.getElementById("sendBtn");
    const chatOutput = document.getElementById("chatOutput");

    sendBtn.addEventListener("click", () => {
        const message = userInput.value;
        if (message.trim() !== "") {
            appendMessage("You", message);
            userInput.value = "";
            fetchResponse(message);
        }
    });

    function appendMessage(sender, message) {
        const messageElement = document.createElement("div");
        messageElement.textContent = `${sender}: ${message}`;
        chatOutput.appendChild(messageElement);
        chatOutput.scrollTop = chatOutput.scrollHeight;
    }

    async function fetchResponse(message) {
        const response = await fetch("/chat", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({ message })
        });
        const data = await response.json();
        appendMessage("AI", data.response);
    }
});
