const mongoose = require("mongoose");

const ApplicationSchema = new mongoose.Schema({
    farmerName: { type: String, required: true },
    mobileNo: { type: String, required: true },
    location: { type: String, required: true },
    address: { type: String, required: true },
    assetDetails: { type: String, required: true },
}, { timestamps: true });

module.exports = mongoose.model("Application", ApplicationSchema);
