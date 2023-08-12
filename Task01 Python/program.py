import json
import os
from datetime import datetime

# --- GLOBAL VARIABLES --- 

file_path = 'notes.json' 

index = 0

#  --- FUNCTIONS ---

# print the string in red
def print_red(text):
    red_code = "\033[91m"
    reset_code = "\033[0m"
    print(red_code + text + reset_code)

# print the string in green
def print_green(text):
    green_code = "\033[92m"
    reset_code = "\033[0m"
    print(green_code + text + reset_code)

# show the interface
def interface():
    print("***************************")
    print("This is a note application:")
    print("To communicate with it use one of the following commands:")
    print(" GET \n ADD \n VIEW \n EDIT \n DELETE \n EXIT")
    print("***************************")

# load the json file
def load_json_file():
    if os.path.exists(file_path):
        with open(file_path, "r") as file:
            try:
                notes = json.load(file)
            except json.JSONDecodeError:
                notes = []
    else:
        notes = []
    return notes

# save a note
def save_note(notes):
    with open(file_path, "w") as file:
        json.dump(notes, file, indent = 4)

# create a note
def create_note(title, body):
    notes = load_json_file()
    global index
    new_note = {
        "id": index,
        "title": title,
        "body": body,
        "created_at": datetime.now().strftime("%Y-%m-%d %H:%M:%S"),
    }
    notes.append(new_note)
    save_note(notes)
    print_green("A note has been created! :)")
    index += 1

# show all notes by date
def list_notes():
    notes = load_json_file()
    if notes:
        sorted_notes = sorted(notes, key=lambda x: x.get("created_at", ""), reverse=True)
        for note in sorted_notes:
            print(f"{note['id']}. {note['title']} ({note['created_at']})")
    else:
        print_red("No any notes :(")

# view a note by id
def view_note(note_id):
    notes = load_json_file()
    print()
    for note in notes:
        if note["id"] == note_id:
            print(f"Title: {note['title']}")
            print(f"Date: {note['created_at']}")
            print(f"Body:\n{note['body']}")
            print()
            return
    print("Заметка с указанным идентификатором не найдена.")

# edit by id
def edit_note(note_id):
    notes = load_json_file()
    for note in notes:
        if note["id"] == note_id:
            new_body = input("NEW BODY: ")
            note["body"] = new_body
            note["updated_at"] = datetime.now().strftime("%Y-%m-%d %H:%M:%S")
            save_note(notes)
            print_green("Note has been edited :)")
            return
    print_red("The index is not found :(")

# delete by id
def delete_note(note_id):
    notes = load_json_file()
    for note in notes:
        if note["id"] == note_id:
            notes.remove(note)
            save_note(notes)
            print_green("Note has been deleted :)")
            return
    print_red("The index is not found :(")

# the main run program
def run():
    notes = load_json_file()
    global index
    index = len(notes) + 1
    interface()
    user_command = input("COMMAND: ")

    while user_command.upper() != "EXIT":

        if user_command.upper() == "GET":
            list_notes()
        elif user_command.upper() == "ADD":
            title = input("TITLE: ")
            body = input("BODY: ")
            create_note(title, body)
        elif user_command.upper() == "VIEW":
            note_id = int(input("Enter index of a note: "))
            view_note(note_id)
        elif user_command.upper() == "EDIT":
            note_id = int(input("Enter index of a note: "))
            edit_note(note_id)
        elif user_command.upper() == "DELETE":
            note_id = int(input("Enter index of a note: "))
            delete_note(note_id)
        else:
            print_red("INVALID COMMAND!")

        interface()
        user_command = input("COMMAND: ")

# --- PROGRAM ---

if __name__ == "__main__":
    run()