// script.js
document.getElementById('invokeButton').addEventListener('click', async () => {
    const resultElement = document.getElementById('result');
    const token = document.getElementById('tokenInput').value;
    resultElement.textContent = 'Invoking...';

    try {
        const response = await fetch('http://localhost:8084/api/invocation/invoke', {
            method: 'POST',
            headers: {
                'Authorization': `${token}`,
                'Content-Type': 'application/json'
            }
        });

        if (!response.ok) {
            throw new Error('Failed to invoke monster');
        }

        const data = await response.json();
        resultElement.textContent = `Monster ID: ${data.monsterId}`;
    } catch (error) {
        resultElement.textContent = "salut" + error.message;
    }
});