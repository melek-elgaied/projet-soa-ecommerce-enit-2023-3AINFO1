 

[
    {
        "group": "catalog",
        "name": "product",
        "items": [
            '{{repeat(56)}}',
            {
                "name": '{{company()}}',
                "picture": 'https://placeimg.com/700/300/tech',
                "pricing": {"amount": '{{floating(0, 500, 2)}}', "currency": "USD"},
                "description": '{{lorem(2, "paragraphs")}}',
                "details": [
                    '{{repeat(5)}}',
                    '{{lorem(4, "words")}}'
                ]
            }
        ]
    }
]