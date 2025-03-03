const apiBaseUrl = 'http://localhost:8080/api';
let token = null;

function register() {
    const username = document.getElementById('register-username').value;
    const password = document.getElementById('register-password').value;

    fetch(`${apiBaseUrl}/auth/register`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ username, password }),
    })
        .then(response => response.json())
        .then(data => {
            alert('Registration successful!');
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Registration failed.');
        });
}

function login() {
    const username = document.getElementById('login-username').value;
    const password = document.getElementById('login-password').value;

    fetch(`${apiBaseUrl}/auth/login`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ username, password }),
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Login failed');
            }
            return response.text();
        })
        .then(data => {
            token = data;
            alert('Login successful!');
            document.getElementById('login').style.display = 'none';
            document.getElementById('profile').style.display = 'block';
            getPlayerProfile();
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Login failed.');
        });
}

function getPlayerProfile() {
    fetch(`${apiBaseUrl}/player/profile`, {
        headers: {
            'Authorization': token,
        },
    })
        .then(response => response.json())
        .then(data => {
            document.getElementById('profile-username').innerText = `Username: ${data.username}`;
            document.getElementById('profile-level').innerText = `Level: ${data.level}`;
            document.getElementById('profile-experience').innerText = `Experience: ${data.experience}`;
            document.getElementById('profile-monsters').innerText = `Monsters: ${data.monsters.join(', ')}`;
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Failed to fetch profile.');
        });
}

function logout() {
    token = null;
    document.getElementById('login').style.display = 'block';
    document.getElementById('profile').style.display = 'none';
}