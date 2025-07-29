document.addEventListener("DOMContentLoaded", () => {
    const registerForm = document.getElementById("registerForm");

    if (registerForm) {
        registerForm.addEventListener("submit", async (e) => {
            e.preventDefault();

            const name = document.getElementById("name").value.trim();
            const email = document.getElementById("email").value.trim();
            const password = document.getElementById("password").value.trim();

            if (!name || !email || !password) {
                alert("Please fill in all fields.");
                return;
            }

            try {
                const response = await fetch("http://localhost:5000/api/admin/register", {
                    method: "POST",
                    headers: { "Content-Type": "application/json" },
                    body: JSON.stringify({ name, email, password }),
                });

                const data = await response.json();
                if (response.ok) {
                    alert("Admin registered successfully!");
                    window.location.href = "admin_login.html"; // Redirect to login page
                } else {
                    alert("Error: " + (data.message || "Registration failed."));
                }
            } catch (error) {
                console.error("Registration error:", error);
                alert("Registration failed. Please try again.");
            }
        });
    } else {
        console.error("Registration form not found!");
    }
});
