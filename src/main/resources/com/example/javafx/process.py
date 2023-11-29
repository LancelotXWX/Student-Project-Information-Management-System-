import cv2
img = cv2.imread('logo.jpg')
new_img = cv2.resize(img,(90,90))
cv2.imwrite('small_logo.jpg',new_img)