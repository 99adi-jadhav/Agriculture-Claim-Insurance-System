const bcrypt = require("bcryptjs");
const jwt = require("jsonwebtoken");
const Officer = require("../model/officer");

// Officer Registration
const registerOfficer = async (req, res) => {
    try {
        const { name, email, password } = req.body;

        // Check if officer already exists
        const existingOfficer = await Officer.findOne({ email });
        if (existingOfficer) {
            return res.status(400).json({ message: "Officer already registered" });
        }

        // Hash password
        const salt = await bcrypt.genSalt(10);
        const hashedPassword = await bcrypt.hash(password, salt);

        // Create and save officer in MongoDB
        const newOfficer = await Officer.create({
            name,
            email,
            password: hashedPassword
        });

        res.status(201).json({
            message: "Officer registered successfully",
            officer: {
                id: newOfficer._id,
                name: newOfficer.name,
                email: newOfficer.email
            }
        });
    } catch (error) {
        console.error("Error registering officer:", error);
        res.status(500).json({ message: "Server error", error: error.message });
    }
};
x
// Officer Login
const loginOfficer = async (req, res) => {
    try {
        const { email, password } = req.body;

        // Check if officer exists
        const officer = await Officer.findOne({ email });
        if (!officer) {
            return res.status(400).json({ message: "Invalid email or password" });
        }

        // Compare passwords
        const isMatch = await bcrypt.compare(password, officer.password);
        if (!isMatch) {
            return res.status(400).json({ message: "Invalid email or password" });
        }

        // Generate JWT token
        const token = jwt.sign({ officerId: officer._id, role: "officer" }, process.env.JWT_SECRET, { expiresIn: "1h" });

        res.json({
            message: "Login successful",
            token,
            officer: {
                id: officer._id,
                name: officer.name,
                email: officer.email
            }
        });
    } catch (error) {
        console.error("Error logging in officer:", error);
        res.status(500).json({ message: "Server error", error: error.message });
    }
};

module.exports = { registerOfficer, loginOfficer };


