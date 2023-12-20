import mongoose, {Schema ,InferSchemaType} from 'mongoose'


const ratingSchema = new Schema( {
    productId : {type : String },
    userId : { type : String} ,
    note : {type: Number } ,},
{
    timestamps: true,
    },
)

type Rating = InferSchemaType<typeof ratingSchema>;

export const RatingModel = mongoose.model<Rating>('Rating',ratingSchema)