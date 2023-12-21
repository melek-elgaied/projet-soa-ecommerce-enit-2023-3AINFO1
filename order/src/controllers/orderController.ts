
import { createOrder, getOrder, getOrderByUser } from '@service/OrderService';
import { Request, Response } from 'express';
export const createOrderAction = async (req: Request, res: Response) => {
    try {
        const order = await createOrder(req.body);
        res.status(201).json(order);
    } catch (error) {
        console.error(error);
        res.status(500).json({ message: 'Internal Server Error' });
    }
};

export const getOrderAction = async (req: Request, res: Response) => {
    try {
        const orderId = req.params.orderId;
        const order = await getOrder(orderId);
        res.status(200).json(order);
    } catch (error) {
        console.error(error);
        res.status(500).json({ message: 'Internal Server Error' });
    }



};

export const getOrderByUserAction = async (req: Request, res: Response) => {
    try {
        const userId = req.params.userId;
        const order = await getOrderByUser(userId);
        res.status(200).json(order);
    } catch (error) {
        console.error(error);
        res.status(500).json({ message: 'Internal Server Error' });
    }

};    