import csv
import re
import requests
import time

def create_slug(title):
    # Remove the leading number and period (e.g. "11. ")
    title = re.sub(r"^\d+\.\s*", "", title)
    title = title.lower()
    # Replace anything that isn't a lowercase letter or number with a hyphen
    title = re.sub(r"[^a-z0-9]+", "-", title)
    return title.strip("-")

def main():
    print("=========================================")
    print("🚀 LeetCode List Importer")
    print("=========================================\n")
    
    print("Please follow these quick steps to get your session credentials:")
    print("1. Go to https://leetcode.com and make sure you are logged in.")
    print("2. Right-click anywhere on the page -> click 'Inspect'.")
    print("3. Go to the 'Application' tab (or 'Storage' if you use Firefox).")
    print("4. On the left sidebar, expand 'Cookies' and click 'https://leetcode.com'.")
    print("5. Find the values for 'LEETCODE_SESSION' and 'csrftoken'.\n")
    
    leetcode_session = input("Paste your LEETCODE_SESSION cookie: ").strip()
    csrftoken = input("Paste your csrftoken cookie: ").strip()
    
    if not leetcode_session or not csrftoken:
        print("Cookies are required to add questions to your account. Exiting.")
        return

    print("\nAwesome! Now, please go to your LeetCode profile, manually create a new empty list (e.g., 'Thita Sheet Questions').")
    list_url = input("Paste the URL of that new list here (e.g., https://leetcode.com/list/xyz123): ").strip()
    
    list_id_match = re.search(r"/(?:list|problem-list)/([a-zA-Z0-9_-]+)", list_url)
    if not list_id_match:
        print("Invalid list URL format. It should look like https://leetcode.com/problem-list/something")
        return
        
    id_hash = list_id_match.group(1)
    print(f"\nTarget List ID Hash: {id_hash}\n")
    
    # Read the downloaded CSV
    slugs = []
    try:
        with open("/tmp/thita_sheet.csv", "r") as f:
            reader = csv.DictReader(f)
            for row in reader:
                q = row.get("Question", "").strip()
                if q:
                    slugs.append(create_slug(q))
    except FileNotFoundError:
        print("Error: Could not find /tmp/thita_sheet.csv. Make sure the file was downloaded.")
        return
                
    print(f"Found {len(slugs)} questions in the sheet. Starting import...\n")
    
    headers = {
        "x-csrftoken": csrftoken,
        "Content-Type": "application/json",
        "Referer": f"https://leetcode.com/list/{id_hash}/",
        "Cookie": f"LEETCODE_SESSION={leetcode_session}; csrftoken={csrftoken}"
    }
    
    success_count = 0
    for i, slug in enumerate(slugs):
        print(f"[{i+1}/{len(slugs)}] Adding '{slug}'...", end=" ")
        
        query_payload = {
            "operationName": "questionTitle",
            "variables": {"titleSlug": slug},
            "query": "query questionTitle($titleSlug: String!) { question(titleSlug: $titleSlug) { questionId } }"
        }
        
        try:
            q_resp = requests.post("https://leetcode.com/graphql/", json=query_payload, headers=headers)
            q_data = q_resp.json()
            question_data = q_data.get("data", {}).get("question")
            if not question_data or not question_data.get("questionId"):
                print("❌ Failed (Could not find question ID for this slug)")
                continue
                
            q_id = str(question_data["questionId"])
            
            payload = {
                "operationName": "addQuestionToFavorite",
                "variables": {
                    "favoriteIdHash": id_hash,
                    "questionId": q_id
                },
                "query": "mutation addQuestionToFavorite($favoriteIdHash: String!, $questionId: String!) {\n  addQuestionToFavorite(favoriteIdHash: $favoriteIdHash, questionId: $questionId) {\n    ok\n    error\n  }\n}\n"
            }
            
            resp = requests.post("https://leetcode.com/graphql/", json=payload, headers=headers)
            data = resp.json()
            
            if "data" in data and data["data"].get("addQuestionToFavorite", {}).get("ok"):
                print("✅ Success")
                success_count += 1
            else:
                print(f"❌ Failed. Response: {data.get('errors', data)}")
        except Exception as e:
            print(f"❌ Error: {e}")
            
        time.sleep(0.5) # Sleep slightly to be polite to LeetCode's servers
        
    print(f"\n🎉 Done! Successfully added {success_count} out of {len(slugs)} questions to your list.")

if __name__ == "__main__":
    main()
