document.getElementById('loginForm').addEventListener('submit', function(event) {
    event.preventDefault();

    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    fetch('/api/authenticate', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ username, password })
    })
        .then(response => response.json())
        .then(data => {
            if (data.token) {
                document.getElementById('message').textContent = 'Login successful!';
                // Save the token and redirect or perform other actions
            } else {
                document.getElementById('message').textContent = 'Invalid credentials';
            }
        })
        .catch(error => {
            document.getElementById('message').textContent = 'An error occurred';
            console.error('Error:', error);
        });
});