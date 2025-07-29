document.addEventListener("DOMContentLoaded", function () {
    // Check if user is logged in
    const userToken = localStorage.getItem("userToken");

    if (!userToken) {
        alert("You must be logged in to access the dashboard.");
        window.location.href = "./user-login.html";
        return;
    }

    console.log("User is logged in! Token:", userToken);

    // Fetch user details (Optional)
    fetch("http://localhost:5000/api/users/me", {
        method: "GET",
        headers: { "Authorization": `Bearer ${userToken}` }
    })
    .then(response => response.json())
    .then(userData => {
        if (userData && userData.email) {
            console.log("User Data:", userData);
            document.querySelector(".dashboard-title").textContent = `Welcome, ${userData.name || 'User'}!`;
        }
    })
    .catch(error => console.error("Error fetching user details:", error));
});

// Logout function
function logout() {
    localStorage.removeItem("userToken");
    window.location.href = "user-login.html";
}
