const mongoose = require("mongoose");

const officerSchema = new mongoose.Schema({
    name: { type: String, required: true, trim: true },
    email: { type: String, required: true, unique: true, trim: true },
    password: { type: String, required: true }
}, { timestamps: true });

const Officer = mongoose.model("Officer", officerSchema);
module.exports = Officer;


