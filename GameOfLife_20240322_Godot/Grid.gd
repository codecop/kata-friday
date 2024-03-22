extends Node2D

const color: = Color(0.8, 0.8, 0.8, 0.1)

onready var viewport: Viewport = get_viewport()
var grid_size := Vector2(20,20)


func _process(delta):
	update()

func _draw():
	var vp_size: = viewport.size
	var vp_right: = vp_size.x
	var vp_bottom: = vp_size.y
	
	var leftmost: = -vp_size.x
	var topmost: = -vp_size.y
	
	var left: = ceil(leftmost / grid_size.x) * grid_size.x
	var bottommost: = vp_bottom + 1
	for x in range(0, vp_size.x / 1 + 1):
		draw_line(Vector2(left, topmost), Vector2(left, bottommost), color)
		left += grid_size.x

	var top: = ceil(topmost / grid_size.y) * grid_size.y
	var rightmost: = vp_right
	for y in range(0, vp_size.y + 1):
		draw_line(Vector2(leftmost, top), Vector2(rightmost, top), color)
		top += grid_size.y
		
	
