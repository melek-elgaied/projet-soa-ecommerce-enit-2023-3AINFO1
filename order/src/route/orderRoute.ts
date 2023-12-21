
import express from 'express';
import { createOrderAction, getOrderAction, getOrderByUserAction } from 'src/controllers/orderController';


const router = express.Router();

router.post('/create', createOrderAction);
router.get('/:orderId', getOrderAction);
router.get('/:userId', getOrderByUserAction);

export default router;