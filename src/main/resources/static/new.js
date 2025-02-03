// Yeni müştəri əlavə etmək üçün frontend kodu

const addNewClient = async () => {
    // Müştəri məlumatları (JSON formatında)
    const newClient = {
        first_name: "Aladdin",
        last_name: "scss",
        fin: "4587877",
        email: "aladdin@gmail.com",
        telephoneNumber: "0503106853",
        address: "Baki",
        age: 20
    };

    try {
        // POST sorğusunu göndəririk
        const response = await fetch('http://localhost:8080/new-client', {
            method: 'POST', // Sorğu metodu POST
            headers: {
                'Content-Type': 'application/json', // Məlumatın formatı JSON
            },
            body: JSON.stringify(newClient) // Məlumatın cəsədi (request body) JSON formatında
        });

        // Sorğu nəticəsini alırıq
        if (response.ok) {
            const responseData = await response.json(); // Backend tərəfindən göndərilən cavab
            console.log('Yeni müştəri əlavə olundu:', responseData);
        } else {
            console.log('Xəta baş verdi:', response.statusText);
        }
    } catch (error) {
        console.log('Xəta:', error);
    }
};

// Funksiyanı çağırırıq
addNewClient();
