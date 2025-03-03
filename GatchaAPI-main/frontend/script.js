const apiBaseUrl = 'http://localhost:8080/api';
let token = null;

function register() {
    const username = document.getElementById('register-username').value;
    const password = document.getElementById('register-password').value;

    const formData = new URLSearchParams();
    formData.append("username", username);
    formData.append("password", password);

    fetch(`${apiBaseUrl}/auth/register`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: formData,
    })
        .then(response => {
            if (!response.ok) throw new Error('Registration failed');
            return response.json();
        })
        .then(data => alert('Registration successful!'))
        .catch(error => alert(error.message));
}

function handleLogin() {
    const username = document.getElementById('login-username').value;
    const password = document.getElementById('login-password').value;

    const formData = new URLSearchParams();
    formData.append("username", username);
    formData.append("password", password);

    fetch(`${apiBaseUrl}/auth/login`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: formData,
    })
        .then(response => {
            if (!response.ok) throw new Error('Login failed');
            return response.text();
        })
        .then(data => {
            token = data;
            alert('Login successful!');
            document.getElementById('login').style.display = 'none';
            document.getElementById('profile').style.display = 'block';

        })
        .catch(error => alert(error.message));
}