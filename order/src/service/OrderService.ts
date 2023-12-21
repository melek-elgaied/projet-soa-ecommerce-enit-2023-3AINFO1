import { Request, Response } from 'express';

import axios from 'axios';
import { OrderModel } from '@model/orderModel';
import { Order } from 'src/type/order';
export const getOrder = async (orderId:string) => {
    try {
        const order = await OrderModel.findById(orderId);
        return order;
    } catch (error) {
        throw error;
    }
};

export const getOrderByUser = async (userId:string) => {
    try {
        const order = await OrderModel.findById(userId);
        return order;
    } catch (error) {
        throw error;
    }
};

const reserveProducts = async (products: any): Promise<boolean> => {
    try {
        const response = await axios.post('http://localhost:3003/api/inventory/reserve', {
            products,
        });

        // Validate the reservation response
        if (response.data && response.data.success) {
            return true;
        } else {
            throw new Error('Failed to reserve products');
        }
    } catch (error) {
        throw error;
    }
};

// Step 2: Process payment
const processPayment = async (userId: string, cost: number): Promise<boolean> => {
    try {
        const paymentResponse = await axios.post('http://localhost:3007/api/payment/processPayment', {
            userId,
            cost,
        });

        // Validate the payment response
        if (paymentResponse.data && paymentResponse.data.isPaid) {
            return true;
        } else {
            throw new Error('Payment failed');
        }
    } catch (error) {
        throw error;
    }
};

// Step 3: Initiate shipping
const initiateShipping = async (userId: string, products: any[], delivery: any): Promise<boolean> => {
    try {
        const shippingResponse = await axios.post('http://localhost:3008/api/shipping/initiateShipping', {
            userId,
            products,
            delivery,
        });

        // Validate the shipping response
        if (shippingResponse.data && shippingResponse.data.success) {
            return true;
        } else {
            throw new Error('Failed to initiate shipping');
        }
    } catch (error) {
        throw error;
    }
};

// Step 4: Create order
export const createOrder = async (orderData: Order) => {
    try {
        // Step 1: Reserve products
        const isReserved = await reserveProducts(orderData.product);

        // Step 2: Process payment
        const cost = orderData.product?.reduce((totalCost: number, product: any) => {
            const productPrice = product.price || 0;
            return totalCost + product.quantity * productPrice;
        }, 0);

        const isPaid = await processPayment(orderData.userId, cost);

        // Step 3: Initiate shipping
        const isShipped = await initiateShipping(orderData.userId, orderData.product, orderData.delivery);

        // Step 4: Create order
        const order = await OrderModel.create({
            userId: orderData.userId,
            cost,
            isReserved,
            isPaid,
            isShipped,
            products: orderData.product,
            delivery: orderData.delivery,
        });

     
    } catch (error) {
        throw error;
    }
};