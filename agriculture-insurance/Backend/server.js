const express = require("express");
const dotenv = require("dotenv");
const cors = require("cors");
const connectDB = require("./config/db");
const adminRoutes = require("./routes/adminRoutes");
const officerRoutes = require("./routes/officerRoutes");
const userRoutes = require("./routes/userRoutes");
const applicationRoutes = require("./routes/applicationRoutes"); // Added for form submission

dotenv.config();

// Connect to MongoDB
connectDB()
    .then(() => console.log(""))
    .catch((err) => {
        console.error("", err);
        process.exit(1);
    });

const app = express();

// Middleware
app.use(express.json());
app.use(cors({ origin: "*" })); // Enable CORS for frontend access

// Routes
app.use("/api/admin", adminRoutes);
app.use("/api/officer", officerRoutes);
app.use("/api/users", userRoutes);
app.use("/api/applications", applicationRoutes); // Route for storing user application data

// 404 Route Not Found Handler
app.use((req, res) => {
    res.status(404).json({ message: "Route not found" });
});

// Global Error Handling Middleware
app.use((err, req, res, next) => {
    console.error("❌ Internal Server Error:", err.message);
    res.status(500).json({ message: "Something went wrong on the server" });
});

const PORT = process.env.PORT || 5000;
app.listen(PORT, () => console.log(`🚀 Server running on port ${PORT}`));
