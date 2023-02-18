create database ClothesShop
GO
use ClothesShop
GO
create table Accounts(
    accID int identity(1,1)primary key,
    email varchar(30)  unique,
    password varchar(30),
    fullname varchar(30),
    phone varchar(12),
    status int check(status =1 or status=0),-- 1:active; 0:inactive
    role int check(role=1 or role=0)    --:admin, 0:user
)
GO
create table Categories(
      CateID int identity(1,1) primary key,
      CateName varchar(30)
)
GO
create table Clothes(
      CID int identity(1,1) primary key,
      CName varchar(30),
      price int check(price>=0),
      imgPath varchar(50),
      description text,
      status int,    --1:active, 0:inactive
      CateID int foreign key references Categories(CateID)
)
GO
create table Orders(
    OrderID int identity(1,1) primary key,
    OrdDate date,
    shipdate date,
    status int check(status =1 or status=2 or status=3),--1:processing, 2: completed, 3: cancel
    AccID int foreign key references Accounts(AccID)
)
GO
create table OrderDetails(
    DetailId int identity(1,1) primary key,
    OrderID int foreign key references Orders(OrderID),
    CID int foreign key references Clothes(CID),
    quantity int check(quantity>=1),	
)
GO
	INSERT INTO dbo.Accounts VALUES ('hoangtienbmt2911@gmail.com', '12345678', 'Pham Hoang Tien', '0868363802', 1, 1)
	INSERT INTO dbo.Accounts VALUES ('donguyenngochanlop9h@gmail.com', '00000000', 'Do Nguyen Ngoc Han', '1234567890', 1, 0)
	INSERT INTO dbo.Categories VALUES ('blouse')
	INSERT INTO dbo.Categories VALUES ('shirt')
	INSERT INTO dbo.Categories VALUES ('dress')
	INSERT INTO dbo.Categories VALUES ('special')
	INSERT INTO dbo.Clothes VALUES ('V-neck Blouse', 30, 'images/V-neck Blouse.jpg', 'V-neck blouse in woven fabric with a sheen.', 1,1)
	INSERT INTO dbo.Clothes VALUES ('Oversized Poplin Shirt', 39, 'images/Oversized Poplin Shirt.jpg', 'Oversized shirt in crisp cotton poplin. Button-down collar, buttons at front, patch chest pocket with button, and a yoke at back.', 1,2)
    INSERT INTO dbo.Clothes VALUES ('Sweatshirt Dress', 63, 'images/Sweatshirt Dress.jpg', 'Short, relaxed-fit sweatshirt dress in cotton-blend fabric. Dropped shoulders, long sleeves, and ribbing at neckline, cuffs, and hem.', 1,3)
    INSERT INTO dbo.Clothes VALUES ('Patterned Special', 50, 'images/Patterned Special.jpg', 'Relaxed-fit shirt in woven viscose fabric with a printed pattern. Collar, buttons at front, heavily dropped shoulders, and long sleeves with a slit at cuffs. Straight-cut hem with slit at sides.', 1,4)

    INSERT INTO dbo.Clothes VALUES ('V-neck Green Blouse', 34, 'images/V-neck Green Blouse.jpg', 'Collar, buttons at front, and long sleeves with a sleeve placket and button at cuffs. Rounded hem.', 1,1)
	INSERT INTO dbo.Clothes VALUES ('Gathered-sleeve Shirt', 37, 'images/Gathered-sleeve Shirt.jpg', 'Oversized shirt in woven fabric. Stand-up collar, concealed buttons at front, and a pleat at back.', 1,2)
	INSERT INTO dbo.Clothes VALUES ('Cotton Poplin Shirt Dress', 50, 'images/Cotton Poplin Shirt Dress.jpg', 'Short, relaxed-fit shirt dress in cotton poplin. Collar, buttons at front, and yoke at back. Gently dropped shoulders, long sleeves with button at cuffs, and a straight-cut hem.', 1,3)
	INSERT INTO dbo.Clothes VALUES ('Oversized Poplin Special', 34, 'images/Oversized Poplin Special.jpg', 'Oversized shirt in crisp cotton poplin. Button-down collar, buttons at front, patch chest pocket with button, and a yoke at back.', 1,4)

    
    INSERT INTO dbo.Clothes VALUES ('Ruffle-trimmed Blouse', 36, 'images/Ruffle-trimmed Blouse.jpg', 'Blouse in airy, woven, crêped fabric. Low, ruffle-trimmed, stand-up collar with narrow ties, V-shaped opening at front, and long.', 1,1)
    INSERT INTO dbo.Clothes VALUES ('Fitted Poplin Shirt', 29, 'images/Fitted Poplin Shirt.jpg', 'Fitted shirt in crisp cotton poplin. Collar, buttons at front, and long sleeves with button at cuffs. Rounded hem.', 1,2)
    INSERT INTO dbo.Clothes VALUES ('Puff-sleeved Dress', 60, 'images/Puff-sleeved Dress.jpg', 'Short dress in woven fabric. Sweetheart neckline, fitted bodice with lacing at center bust, and a smocked section at back.', 1,3)
    INSERT INTO dbo.Clothes VALUES ('Oversized Tie-top Special', 36, 'images/Oversized Tie-top Special.jpg', 'Oversized blouse in softly draped satin. Round neckline with narrow trim, V-shaped opening with narrow ties at top, and voluminous, elbow-length sleeves with narrow trim at cuffs.', 1,4)

    
	INSERT INTO dbo.Clothes VALUES ('V-neck Black Blouse', 32, 'images/V-neck Black Blouse.jpg', 'V-neck blouse in woven fabric with a sheen. Collar, buttons at front, and long sleeves with a sleeve placket and button at cuffs.', 1,1)
    INSERT INTO dbo.Clothes VALUES ('Oxford Shirt', 40, 'images/Oxford Shirt.jpg', 'Relaxed-fit shirt in washed oxford cotton. Collar, buttons at front, and yoke at back with locker loop.', 1,2)
    INSERT INTO dbo.Clothes VALUES ('Wrap Jacket Dress', 55, 'images/Wrap Jacket Dress.jpg', 'Short, fitted jacket dress in a woven viscose and linen blend. Notched lapels, a chest pocket, and welt front pockets with flap.', 1,3)
    INSERT INTO dbo.Clothes VALUES ('Crinkled Chiffon Special', 42, 'images/Crinkled Chiffon Special.jpg', 'Relaxed-fit shirt in crinkled chiffon. Collar, buttons at front, dropped shoulders, and long sleeves with button at cuffs. Rounded hem, slightly longer at back.', 1,4)
    

    
    
