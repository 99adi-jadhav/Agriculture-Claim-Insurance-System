document.querySelector("form").addEventListener("submit", async function (event) {
    event.preventDefault();

    const email = document.getElementById("inputEmail").value;

    try {
        const response = await fetch("http://localhost:5000/api/officer/forgot-password", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ email }),
        });

        const data = await response.json();
        if (response.ok) {
            alert("Password reset link sent to your email.");
        } else {
            alert(data.message);
        }
    } catch (error) {
        console.error("Forgot password error:", error);
        alert("An error occurred. Please try again.");
    }
});
