import React, { useContext } from 'react';
import api from '../services/api';
import { AuthContext } from '../context/AuthContext';

const ProductTile = ({ product }) => {
  const { user } = useContext(AuthContext);

  const handleBuyNow = async () => {
    try {
      await api.post('/cart/add', {
        productId: product.id,
        userId: user.userId,
        quantity: 1,
      });
      alert('Product added to cart!');
    } catch (err) {
      console.error('Add to cart failed:', err);
      alert('Failed to add product to cart.');
    }
  };

  return (
    <div style={styles.card}>
      <h4>{product.name}</h4>
      <p>{product.description}</p>
      <p><strong>Price: ₹{product.price}</strong></p>
      <button onClick={handleBuyNow} style={styles.button}>Buy Now</button>
    </div>
  );
};

const styles = {
  card: {
    border: '1px solid #ccc',
    padding: '15px',
    borderRadius: '10px',
    textAlign: 'center',
  },
  button: {
    marginTop: '10px',
    padding: '10px',
    fontSize: '14px',
    backgroundColor: '#4CAF50',
    color: 'white',
    border: 'none',
    borderRadius: '5px',
    cursor: 'pointer',
  },
};

export default ProductTile;
