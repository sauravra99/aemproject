fetch(form.action, {
    method: "POST",
    body: new FormData(form)
}).then(response => response.json())
  .then(data => {
      // After successful submission, redirect to thank-you page
      window.location.href = "/content/mysite/us/en/thank-you.html";
  });
