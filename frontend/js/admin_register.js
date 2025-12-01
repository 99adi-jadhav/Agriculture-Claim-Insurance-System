document.addEventListener("DOMContentLoaded", () => {
    const registerForm = document.getElementById("registerForm");

    if (!registerForm) {
        console.error("Registration form not found!");
        return;
    }

    registerForm.addEventListener("submit", async (e) => {
        e.preventDefault();

        const name = document.getElementById("name").value.trim();
        const email = document.getElementById("email").value.trim();
        const password = document.getElementById("password").value.trim();

        if (!name || !email || !password) {
            alert("Please fill in all fields.");
            return;
        }

        // Simple email format check (optional)
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!emailRegex.test(email)) {
            alert("Please enter a valid email address.");
            return;
        }

        try {
            console.log("Sending registration request...", { name, email });

            // Make sure the port matches your Spring Boot server
            const API_URL = "http://localhost:5000/api/admin/register";

            const response = await fetch(API_URL, {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ name, email, password }),
            });

            // Try parse JSON, but guard if it's not JSON
            let data;
            try {
                data = await response.json();
            } catch (parseErr) {
                data = { message: `Unexpected response (status ${response.status})` };
            }

            console.log("Response status:", response.status, "body:", data);

            if (response.ok) {
                alert(data.message || "Admin registered successfully!");
                // short delay makes the alert readable
                setTimeout(() => window.location.href = "admin_login.html", 800);
            } else {
                alert("Error: " + (data.message || "Registration failed."));
            }
        } catch (error) {
            console.error("Registration error:", error);
            alert("Registration failed. Please check console and try again.");
        }
    });
});
