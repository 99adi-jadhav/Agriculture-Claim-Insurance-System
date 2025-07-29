document.addEventListener("DOMContentLoaded", function () {
    document.getElementById("loginForm").addEventListener("submit", async function (event) {
        event.preventDefault();

        const email = document.getElementById("email").value.trim();
        const password = document.getElementById("password").value.trim();

        if (!email || !password) {
            alert("Please fill in all fields!");
            return;
        }

        try {
            const response = await fetch("http://localhost:5000/api/users/login", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ email, password })
            });

            const result = await response.json();

            if (!response.ok) {
                throw new Error(result.message || "Login failed! Please try again.");
            }

            // Store Token & Redirect
            localStorage.setItem("userToken", result.token);
            console.log("User logged in! Redirecting...");
            window.location.href = "./user-home.html";

        } catch (error) {
            console.error("Login error:", error.message);
            alert(error.message);
        }
    });
});
