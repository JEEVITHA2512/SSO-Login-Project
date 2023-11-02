import React, { useState } from 'react';
import './order.css';

function ProductForm() {
  const [name, setName] = useState('');
  const [category, setCategory] = useState('');
  const [price, setPrice] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();

    // Backend URL where you want to send the POST request
    const apiUrl = 'http://localhost:8002/api/add/order';

    // Create a data object with individual parameters
    const data = {
      name,
      category,
      price: parseInt(price), // Convert price to a integer
    };

    console.log("s",JSON.stringify(data));

    // Create the POST request
    fetch(apiUrl, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(data),
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        return response.json();
      })
      .then((data) => {
        // Handle the response data as needed (e.g., display a success message)
        console.log('Success:', data);
      })
      .catch((error) => {
        // Handle any errors that occur during the fetch request (e.g., display an error message)
        console.error('Error:', error);
      });

    // Clear the form after submission
    setName('');
    setCategory('');
    setPrice('');
  };

  return (
    <div className='box'>
      <h1>Add a New Product</h1>
      <form onSubmit={handleSubmit}>
        <div>
          <label htmlFor="name">Name:</label>
          <input
          className='field'
            type="text"
            id="name"
            value={name}
            onChange={(e) => setName(e.target.value)}
            required
            placeholder='Product Name'
          />
        </div>
        <div>
          <label htmlFor="category">Category:</label>
          <input className='field'
            type="text"
            id="category"
            value={category}
            onChange={(e) => setCategory(e.target.value)}
            required
            placeholder='Product Category'
          />
        </div>
        <div>
          <label htmlFor="price">Price:</label>
          <input className='field'
            type="number"
            id="price"
            value={price}
            onChange={(e) => setPrice(e.target.value)}
            required
            placeholder='Product Price'
          />
        </div>
        <button className='btn1' type="submit">Submit</button>
      </form>
    </div>
  );
}

export default ProductForm;
