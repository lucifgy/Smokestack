import re
import json
import time
import random
from requests import utils, get

# List of tickers
tickers = [
    "ARCC", "BBDC", "BCSF", "BXSL", "CCAP", "CGBD", "CION", "CSWC", "EQS",
    "FDUS", "FSEN", "FSK", "GAIN", "GBDC", "GECC", "GLAD", "GSBD", "HRZN",
    "HTGC", "ICMB", "KBDC", "LIEN", "LRFC", "MAIN", "MFIC", "MRCC", "MSDL",
    "NCDL", "NMFC", "OBDC", "OBDE", "OCSL", "OFS", "OXSQ", "PFLT", "PFX",
    "PIAC", "PNNT", "PSBD", "PSEC", "PTMN", "RAND", "RWAY", "SAR", "SCM",
    "SLRC", "SSSS", "TCPC", "TPVG", "TRIN", "TSLX"
]

# Function to fetch page content with headers
def get_page(ticker):
    headers = utils.default_headers()
    headers.update({
        'User-Agent': 'Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:52.0) Gecko/20100101 Firefox/52.0',
    })
    url = f"https://www.zacks.com/stock/research/{ticker}/earnings-calendar?icid=quote-stock_overview-quote_nav_tracking-zcom-left_subnav_quote_navbar-earnings_dates_announcements"
    response = get(url, headers=headers)
    return response

# Loop through each ticker
for ticker in tickers:
    print(f"Fetching data for ticker: {ticker}")
    response = get_page(ticker)

    # Check if the response status is 200 (OK)
    if response.status_code == 200:
        html_content = response.text

        # Extract the JavaScript object using regex
        match = re.search(r'document\.obj_data\s*=\s*({.*?});', html_content, re.DOTALL)
        if match:
            json_data = match.group(1)

            # Parse the JSON data
            try:
                parsed_data = json.loads(json_data)
                earnings_table = parsed_data.get("earnings_announcements_earnings_table", [])
                if earnings_table:
                    # Extract the last element (e.g., "Before Open")
                    first_entry = earnings_table[0]  # The first earnings entry
                    before_open_info = first_entry[-1]  # The last element is the time information
                    print(f"{ticker} {before_open_info}")
                else:
                    print(f"NA")
            except json.JSONDecodeError:
                print(f"{ticker} - Error parsing JSON data.")
        else:
            print(f"{ticker} - Data object not found in the page.")
    else:
        print(f"{ticker} - Failed to fetch data. Status code: {response.status_code}")

    # Random delay between 30-60 seconds
    delay = random.randint(1, 5)
    time.sleep(delay)

print("Data fetching complete.")
