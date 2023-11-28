from telethon import TelegramClient, events
from os import system
from getpass import getpass
import json

print('id:')
x = getpass()
print('pass:')
y = getpass()
print('chat id:')
chat_id = getpass()

client = TelegramClient('anon', x, y).start()
print("Ctrl + C to exit")

count = 0;

@client.on(events.NewMessage(chats=chat_id))
async def nm_handler(event):
    a = await client.get_messages(chat_id)
    await client.send_message(chat_id, '1', reply_to=a[0].id)
    count += 1
    print("Reply no: ", count)

    # await client.send_message('me', "Hello again")
    # await client.sendmessage(channel, 'Ignore this', comment_to=1583)
    # a = await client.get_messages(chat_id)
    # print(a[0].id)

with client:
    client.run_until_disconnected()
