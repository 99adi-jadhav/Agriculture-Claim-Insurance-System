document.getElementById("registerForm").addEventListener("submit", async function (event) {
    event.preventDefault();

    const name = document.getElementById("name").value.trim();
    const email = document.getElementById("email").value.trim();
    const password = document.getElementById("password").value.trim();

    if (!name || !email || !password) {
        alert("Please fill in all fields.");
        return;
    }

    try {
        const response = await fetch("http://localhost:5000/api/officer/register", {  // Ensure this matches backend
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ name, email, password }),
        });

        const data = await response.json();
        if (response.ok) {
            alert("Registration successful! You can now log in.");
            window.location.href = "officer_login.html";
        } else {
            alert(data.message || "Registration failed. Please try again.");
        }
    } catch (error) {
        console.error("Registration error:", error);
        alert("Server error. Please check your connection and try again.");
    }
});
