document.addEventListener("DOMContentLoaded", function () {
    document.getElementById("registerForm").addEventListener("submit", async function (event) {
        event.preventDefault();

        const name = document.getElementById("name").value.trim();
        const email = document.getElementById("email").value.trim();
        const password = document.getElementById("password").value.trim();
        const confirmPassword = document.getElementById("confirmPassword").value.trim();

        if (!name || !email || !password || !confirmPassword) {
            alert("Please fill in all fields!");
            return;
        }

        if (password !== confirmPassword) {
            alert("Passwords do not match!");
            return;
        }

        try {
            const response = await fetch("http://localhost:5000/api/users/register", { // Corrected route
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ name, email, password }) // Removed confirmPassword from request
            });

            const result = await response.json();

            if (!response.ok) {
                throw new Error(result.message || "Registration failed! Please try again.");
            }

            alert("Registration successful! User stored in database.");
            window.location.href = "./user-login.html";

        } catch (error) {
            alert(error.message);
        }
    });
});
