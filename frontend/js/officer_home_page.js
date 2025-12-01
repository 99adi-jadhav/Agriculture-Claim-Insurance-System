document.addEventListener("DOMContentLoaded", function () {
    const token = localStorage.getItem("officerToken");

    if (!token) {
        alert("You must be logged in to access this page.");
        window.location.href = "officer_login.html"; // Redirect to login page
    } else {
        console.log("Officer is authenticated.");
    }
});

// Logout function
document.getElementById("logoutBtn")?.addEventListener("click", function () {
    localStorage.removeItem("officerToken");
    alert("Logged out successfully!");
    window.location.href = "officer_login.html";
});
