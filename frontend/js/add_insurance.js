document.addEventListener("DOMContentLoaded", () => {
    const insuranceForm = document.getElementById("insuranceForm");

    insuranceForm.addEventListener("submit", async (e) => {
        e.preventDefault();

        const policyName = document.getElementById("policyName").value.trim();
        const cropType = document.getElementById("cropType").value;
        const coverage_amount = document.getElementById("coverage").value;
        const premium = document.getElementById("premium").value;
        const description = document.getElementById("description").value.trim();

        if (!policyName || !cropType || !coverage_amount || !premium || !description) {
            alert("Please fill in all fields.");
            return;
        }

        try {
            const response = await fetch("http://localhost:5000/api/admin/insurance/add", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({
                    name: policyName,
                    crop_type: cropType,
                    coverage_amount: parseFloat(coverage_amount),   // ✔ matches backend
                    premium: parseFloat(premium),
                    description: description
                }),
            });

            const data = await response.json();

            if (response.ok) {
                alert("Insurance added successfully!");
                insuranceForm.reset();
            } else {
                alert("Error: " + data.error);
            }
        } catch (err) {
            console.error("Add insurance error:", err);
            alert("Request failed!");
        }
    });
});
