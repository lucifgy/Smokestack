import requests

api_url = 'https://api.telegram.org/bot6440633234:AAF0fL3N9IhlfCW7TkmqJS7Q1p20XnsZQm0'

get_me = '/getMe'
send_msg = '/sendMessage'
update = '/getUpdates'

response = requests.get(api_url + update)

print(response.json())

response = requests.get(api_url)

postargs = {"chat_id": "815026778", "text": "Hi"}

response = requests.post(api_url + send_msg, postargs)

print(response.json())
