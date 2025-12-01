document.addEventListener("DOMContentLoaded", function () {
    // Check if user is logged in
    const userId = localStorage.getItem("userId");

    if (!userId) {
        alert("You must be logged in to access the dashboard.");
        window.location.href = "user-login.html";
        return;
    }

    console.log("User is logged in! userId:", userId);

    // Fetch user details (Optional)
    fetch(`http://localhost:5000/api/users/me?userId=${userId}`)
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
    localStorage.removeItem("userId");
    window.location.href = "user-login.html";
}
