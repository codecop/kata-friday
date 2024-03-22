extends Area2D

class_name Cell

# Declare member variables here. Examples:
# var a = 2
# var b = "text"

# Called when the node enters the scene tree for the first time.
func _ready():
	print("ready")

func _process(delta):
	var areas = get_overlapping_areas()
	var neighboursCount = areas.size()
	print(neighboursCount)
	
	if (neighboursCount < 3):
		print("removing")
		get_parent().remove_child(self)

func _on_Cell_body_entered(body):
	print("body entered")
	print(body)
