# EGPAYTASK
Implementation of a simple input activity using MVP pattern.

![](task_tut.gif)

## SPECEFICATIONS
-	Create an activity, dynamically from the JSON file provided (data.json) in run time.
-	Attributes properties in the activity are given in the JSON file (ex: id, type, hint, max length...).
-	If Spinner view, check the selected default value.
-	Validate and display validation error of inputs according to the validation set in the JSON response provided.
-	JSON Ex.
-	id: input id (you should use this as key in post request and typed value as value)
-	name: label name 
-	required: if yes means the input must be required else optional 
-	type: 
-	number: number input
-	date: date input format (yyyy-mm-dd)
-	string : edit text input 
-	textarea: multiline edit text
-	select: spinner input should fill with (multiple)
-	default_value: to be set in input by default 
-	sort: you should sort inputs by ASC
-	After create form you should sent data as POST request to following URL: http://101.amrbdr.com/
-	Ex Sending Data
-	[input id form JSON file => typed value be user]
-	[1528 => 'Amr Alaa', 1529=> '21212012365214']
-	Display response in dialog.

## GENERAL NOTES
-	Write the code using Java language.
-	Do not use XML to create activity components.
-	We are keen on simplicity, easy-to-read code, user-friendly User interface and bug-free code.	
-	You are free to use libraries, but leave references & comments about it.
-	You are free to use design pattern, MVP recommended.
-	Unit Test is bonus.

## DEPENDENCIES USED
- Retrofit
- JSON Parsing using GSON
- OKHTTP
- Scalling using sdp
