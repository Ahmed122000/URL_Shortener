document.getElementById('url-form').addEventListener('submit', async(event)=>{
    event.preventDefault();

    const rawText = document.getElementById('url').value;

    //change this url to change the server url (e.g. http://localhost:8080)
    const url = 'https://url-shortener-k5fr.onrender.com'
    const fullUrl = `${url}/api/url`
    
    try{
        const response = await fetch(fullUrl, 
        {
            method:"POST",
            headers:{
                'Content-Type':'text/plain',
            },
            body:rawText, 
        });

        if(response.ok){
            const result = await response.json();
            const shortUrl = result['shortUrl'];
            //console.log(`result: ${result['shortUrl']}`)
            updateUI(shortUrl);
            alert('Operation is done successfully')
            document.getElementById('url').value='';
        }
        else{
            console.error('Error:', response.statusText);
            alert(`Submission failed: ${response.statusText}`);
        }
    }catch(error){
        console.error("Error", error)
        alert("An error occurred while retrieving the URL. Please try again.");
    }
})


function updateUI(result) {
    const resultArea = document.getElementById('result');
    console.log("result: " + result)
    resultArea.innerHTML = `
    <h5>Shortened URL</h5>
    <p>
        <a href="${result}" target="_blank">${result}</a>
        <button id="copyButton">
            <i class="fas fa-copy"></i>
        </button>
    </p>
`;

    const copyButton = document.getElementById('copyButton');
    copyButton.addEventListener('click', () => {
        navigator.clipboard.writeText(result)
    });
}

