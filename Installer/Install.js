const fs = require("fs")
const { exec } = require("child_process")

let clientName = "void"

let appdataFolder = process.env.APPDATA || (process.platform == 'darwin' ? process.env.HOME + '/Library/Preferences' : process.env.HOME + "/.local/share")
let mcFolder = appdataFolder +"/.minecraft"
let versionsfolder = mcFolder + "/versions"
let clientFolder = versionsfolder + `/${clientName}/`
let exportFolder = "./repo/Export/Void/"

let jarFile = exportFolder + "Void.jar"
let jarFileContents = ""

let jsonFile = exportFolder + "Void.json"
let jsonFileContents = ""

exec("git clone https://github.com/darkboat/Void-Client.git repo")

jarFileContents = fs.readFileSync(jarFile)
jsonFileContents = fs.readFileSync(jsonFile)

fs.promises.writeFile(clientFolder + "Void.jar", jarFileContents)
fs.promises.writeFile(clientFolder + "Void.json", jsonFileContents)