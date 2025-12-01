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

            // Store userId
            localStorage.setItem("userId", result.userId);

            alert("Login Successful!");
            console.log("Redirecting to Apply Insurance page...");

            // Redirect to apply insurance page
            window.location.href = "../html/user/apply_insurance.html";

        } catch (error) {
            console.error("Login error:", error.message);
            alert(error.message);
        }
    });

});
