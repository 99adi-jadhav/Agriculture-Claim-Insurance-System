document.addEventListener("DOMContentLoaded", () => {
    const loginForm = document.getElementById("loginForm");

    loginForm.addEventListener("submit", async (e) => {
        e.preventDefault();

        const email = document.getElementById("email").value.trim();
        const password = document.getElementById("password").value.trim();

        if (!email || !password) {
            alert("Please fill in all fields.");
            return;
        }

        try {
            const response = await fetch("http://localhost:5000/api/admin/login", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ email, password }),
            });

            const data = await response.json();
            if (response.ok) {
                alert("Login successful!");
                localStorage.setItem("adminId", data.adminId);
                window.location.href = "admin_home_page.html";
            } else {
                alert("Error: " + (data.message || "Invalid credentials."));
            }
        } catch (error) {
            console.error("Login error:", error);
            alert("Failed to login. Please try again.");
        }
    });
});
