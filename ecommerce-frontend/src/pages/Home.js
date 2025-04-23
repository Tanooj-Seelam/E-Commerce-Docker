import React, { useEffect, useState } from 'react';
import api from '../services/api';
import ProductTile from '../components/ProductTile';

const Home = () => {
  const [products, setProducts] = useState([]);
  const [searchTerm, setSearchTerm] = useState('');

  const fetchAllProducts = async () => {
    try {
      const res = await api.get('/products/home');
      setProducts(res.data);
    } catch (err) {
      console.error('Error fetching products:', err);
    }
  };

  const searchProducts = async () => {
    try {
      if (searchTerm.trim() === '') {
        fetchAllProducts();
      } else {
        const res = await api.get(`/products/search?name=${searchTerm}`);
        setProducts(res.data);
      }
    } catch (err) {
      console.error('Search failed:', err);
    }
  };

  useEffect(() => {
    fetchAllProducts();
  }, []);

  return (
    <div style={styles.page}>
      <h2>Welcome to E-Shop</h2>
      <input
        type="text"
        placeholder="Search products..."
        value={searchTerm}
        onChange={(e) => setSearchTerm(e.target.value)}
        onKeyDown={(e) => e.key === 'Enter' && searchProducts()}
        style={styles.search}
      />
      <div style={styles.grid}>
        {products.map((product) => (
          <ProductTile key={product.id} product={product} />
        ))}
      </div>
    </div>
  );
};

const styles = {
  page: {
    padding: '20px',
    maxWidth: '1000px',
    margin: '0 auto',
  },
  search: {
    width: '100%',
    padding: '10px',
    fontSize: '16px',
    marginBottom: '20px',
  },
  grid: {
    display: 'grid',
    gridTemplateColumns: 'repeat(auto-fill, minmax(250px, 1fr))',
    gap: '20px',
  },
};

export default Home;
