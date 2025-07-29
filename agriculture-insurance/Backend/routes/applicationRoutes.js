    const express = require("express");
    const Application = require("../model/Application");

    const router = express.Router();

    // Submit application form (POST)
    router.post("/submit", async (req, res) => {
        try {
            const newApplication = new Application(req.body);
            const savedApplication = await newApplication.save();
            res.status(201).json(savedApplication);
        } catch (error) {
            console.error("❌ Error submitting application:", error);
            res.status(500).json({ message: "Failed to submit application" });
        }
    });

    // Get all applications (GET)
    router.get("/", async (req, res) => {
        try {
            const applications = await Application.find();
            res.status(200).json(applications);
        } catch (error) {
            console.error("❌ Error fetching applications:", error);
            res.status(500).json({ message: "Failed to retrieve applications" });
        }
    });

    module.exports = router;
