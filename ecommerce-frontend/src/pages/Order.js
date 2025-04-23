import React, { useEffect, useState, useContext } from 'react';
import api from '../services/api';
import { AuthContext } from '../context/AuthContext';

const Orders = () => {
  const { user } = useContext(AuthContext);
  const [orders, setOrders] = useState([]);

  const fetchOrders = async () => {
    try {
      const res = await api.get(`/orders/all/${user.userId}`);
      setOrders(res.data);
    } catch (err) {
      console.error('Error fetching orders:', err);
    }
  };

  const cancelOrder = async (orderId) => {
    try {
      await api.delete(`/orders/cancel/${orderId}`);
      fetchOrders();
      alert('Order cancelled successfully!');
    } catch (err) {
      console.error('Error cancelling order:', err);
    }
  };

  useEffect(() => {
    fetchOrders();
  }, []);

  return (
    <div style={styles.page}>
      <h2>Your Orders</h2>
      {orders.length === 0 ? (
        <p>No orders found.</p>
      ) : (
        orders.map((order) => (
          <div key={order.id} style={styles.card}>
            <h4>Order ID: {order.id}</h4>
            <p><strong>Date:</strong> {new Date(order.createdAt).toLocaleDateString()}</p>
            <p><strong>Status:</strong> {order.status}</p>
            <p><strong>Total:</strong> ₹{order.totalAmount}</p>
            <button style={styles.button} onClick={() => cancelOrder(order.id)}>Cancel Order</button>
          </div>
        ))
      )}
    </div>
  );
};

const styles = {
  page: {
    padding: '20px',
    maxWidth: '800px',
    margin: '0 auto',
  },
  card: {
    border: '1px solid #ccc',
    borderRadius: '8px',
    padding: '15px',
    marginBottom: '15px',
  },
  button: {
    backgroundColor: '#f44336',
    color: '#fff',
    padding: '8px 12px',
    border: 'none',
    cursor: 'pointer',
  },
};

export default Orders;
