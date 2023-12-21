import mongoose, { Schema, InferSchemaType } from 'mongoose';





const orderSchema = new Schema(
  {
    userId: { type: String, required: true },
    cost :{type: Number, required: true},
    isReserved: { type: Boolean, default: false },
    isPaid: { type: Boolean, default: false },
    
    product:[ {
        productId: { type: String},
        quantity: { type: String, index: 'text' },
        price:{type: Number}
      }],
    
    delivery:[ {
        adress: { type: String, index: 'text' },
        zip : { type: String, index: 'text' },
        
      }],  
  },
  {
    timestamps: true,
  }
);

type Order = InferSchemaType<typeof orderSchema>;

export const OrderModel = mongoose.model<Order>('Order', orderSchema);