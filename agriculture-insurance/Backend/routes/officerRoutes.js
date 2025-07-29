const express = require("express");
const { registerOfficer, loginOfficer } = require("../controllers/officerController");

const router = express.Router();

// Define routes
router.post("/register", registerOfficer);
router.post("/login", loginOfficer);

module.exports = router;
