<h1 align="center">⚖️ SMART\_BMI</h1>



<p align="center">

&nbsp; <img src="https://img.shields.io/badge/Maintained-yes-green.svg" />

&nbsp; <img src="https://img.shields.io/badge/Android-Java-orange.svg" />

&nbsp; <img src="https://img.shields.io/badge/UI-Material--Design--3-blue.svg" />

</p>



<p align="center">

&nbsp; <b>SMART\_BMI</b> is a simple Android app that helps users calculate and track their BMI easily.<br>

&nbsp; It focuses on a clean UI, smooth user experience, and saving recent BMI records.

</p>



---



\## ✨ Features



<ul>

&nbsp; <li><b>🎨 Clean \& Simple UI:</b> Uses Material Design 3 components with a soft lavender theme.</li>

&nbsp; <li><b>📏 Easy Height Selection:</b> Height is selected using a custom SeekBar for better accuracy.</li>

&nbsp; <li><b>💾 Remembers Your Data:</b> Saves the last entered Age, Weight, and Height using <code>SharedPreferences</code>.</li>

&nbsp; <li><b>💡 Controlled Saving:</b> BMI is calculated instantly, but history is saved only when the user clicks the <b>Save</b> button.</li>

&nbsp; <li><b>📋 Recent History:</b> Stores and displays the last 5 BMI records for quick reference.</li>

</ul>



---



\## 📸 App Preview



<table align="center">

&nbsp; <tr>

&nbsp;   <td align="center"><b>Main Screen</b></td>

&nbsp;   <td align="center"><b>Result Screen</b></td>

&nbsp;   <td align="center"><b>History</b></td>

&nbsp; </tr>

&nbsp; <tr>

&nbsp;   <td><img src="images/main\_screen.png" width="250" /></td>

&nbsp;   <td><img src="images/result\_screen.png" width="250" /></td>

&nbsp;   <td><img src="images/history\_dialog.png" width="250" /></td>

&nbsp; </tr>

</table>



---



\## ⚙️ How It Works



<p>The app follows a simple flow to manage BMI history:</p>



<ol>

&nbsp; <li><b>User Input:</b> Age, weight, and height are entered using Material input fields.</li>

&nbsp; <li><b>Calculation:</b> Data is passed to the result screen where BMI is calculated.</li>

&nbsp; <li><b>Saving History:</b> When the user clicks <b>Save</b>:

&nbsp;   <ul>

&nbsp;     <li>Previous records are loaded from <code>SharedPreferences</code>.</li>

&nbsp;     <li>GSON converts stored data into a list.</li>

&nbsp;     <li>The new record is added to the top of the list.</li>

&nbsp;     <li>If more than 5 records exist, the oldest one is removed.</li>

&nbsp;     <li>The updated list is saved again.</li>

&nbsp;   </ul>

&nbsp; </li>

</ol>



---



\## 🛠️ Tech Stack



<ul>

&nbsp; <li><b>Language:</b> Java</li>

&nbsp; <li><b>UI:</b> Material Design 3, CardView, ScrollView</li>

&nbsp; <li><b>Storage:</b> SharedPreferences</li>

&nbsp; <li><b>Library:</b> GSON (for saving BMI history)</li>

</ul>



---



\## 📥 Installation



<pre>

1\. git clone https://github.com/svidhi08/SMART\_BMI.git

2\. Add GSON dependency:

&nbsp;  implementation 'com.google.code.gson:gson:2.10.1'

3\. Sync the project and run in Android Studio

</pre>



<p align="center">

&nbsp; Developed by <a href="https://github.com/svidhi08">svidhi08</a>

</p>

