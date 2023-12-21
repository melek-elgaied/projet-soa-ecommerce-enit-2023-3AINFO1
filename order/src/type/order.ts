export interface Order {userId: string;
    product:{
        productId: string;
      quantity: string;
      price:Number;
    }[];
    cost:Number
    isReserved?:Boolean;
    isPaid :Boolean;
    delivery: {
        adress: string;
        zip: string;
      
    }[];
}

