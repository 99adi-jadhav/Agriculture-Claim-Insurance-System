document.addEventListener("DOMContentLoaded", () => {
    const adminToken = localStorage.getItem("adminToken");

    if (!adminToken) {
        alert("You must be logged in to access this page.");
        window.location.href = "admin_login.html";
        return;
    }

    console.log("Admin is authenticated.");
});

// Logout function
function handleLogout() {
    localStorage.removeItem("adminToken");
    alert("Logged out successfully!");
    window.location.href = "admin_login.html";
}
