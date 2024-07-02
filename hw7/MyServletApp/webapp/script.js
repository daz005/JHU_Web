
document.getElementById('dataForm').addEventListener('submit', function(event) {
    event.preventDefault();

    const name = document.getElementById('name').value;

    fetch('http://localhost:8080/MyServletApp/MyServlet', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ name: name })
    })
    .then(response => response.text())
    .then(data => {
        document.getElementById('response').innerText = data;
    })
    .catch(error => console.error('Error:', error));
});
