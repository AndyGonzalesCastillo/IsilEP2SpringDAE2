USE [master]
GO
/****** Object:  Database [SistemaParcial]    Script Date: 01/04/2025 17:46:04 ******/
CREATE DATABASE [SistemaParcial]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'SistemaParcial', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.MSSQLSERVER\MSSQL\DATA\SistemaParcial.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'SistemaParcial_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.MSSQLSERVER\MSSQL\DATA\SistemaParcial_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT, LEDGER = OFF
GO
ALTER DATABASE [SistemaParcial] SET COMPATIBILITY_LEVEL = 160
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [SistemaParcial].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [SistemaParcial] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [SistemaParcial] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [SistemaParcial] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [SistemaParcial] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [SistemaParcial] SET ARITHABORT OFF 
GO
ALTER DATABASE [SistemaParcial] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [SistemaParcial] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [SistemaParcial] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [SistemaParcial] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [SistemaParcial] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [SistemaParcial] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [SistemaParcial] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [SistemaParcial] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [SistemaParcial] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [SistemaParcial] SET  ENABLE_BROKER 
GO
ALTER DATABASE [SistemaParcial] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [SistemaParcial] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [SistemaParcial] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [SistemaParcial] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [SistemaParcial] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [SistemaParcial] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [SistemaParcial] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [SistemaParcial] SET RECOVERY FULL 
GO
ALTER DATABASE [SistemaParcial] SET  MULTI_USER 
GO
ALTER DATABASE [SistemaParcial] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [SistemaParcial] SET DB_CHAINING OFF 
GO
ALTER DATABASE [SistemaParcial] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [SistemaParcial] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [SistemaParcial] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [SistemaParcial] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'SistemaParcial', N'ON'
GO
ALTER DATABASE [SistemaParcial] SET QUERY_STORE = ON
GO
ALTER DATABASE [SistemaParcial] SET QUERY_STORE (OPERATION_MODE = READ_WRITE, CLEANUP_POLICY = (STALE_QUERY_THRESHOLD_DAYS = 30), DATA_FLUSH_INTERVAL_SECONDS = 900, INTERVAL_LENGTH_MINUTES = 60, MAX_STORAGE_SIZE_MB = 1000, QUERY_CAPTURE_MODE = AUTO, SIZE_BASED_CLEANUP_MODE = AUTO, MAX_PLANS_PER_QUERY = 200, WAIT_STATS_CAPTURE_MODE = ON)
GO
USE [SistemaParcial]
GO
/****** Object:  Table [dbo].[Accesorio]    Script Date: 01/04/2025 17:46:04 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Accesorio](
	[codigo] [int] IDENTITY(1,1) NOT NULL,
	[nombre] [varchar](100) NOT NULL,
	[tipo] [varchar](20) NOT NULL,
	[costo] [float] NOT NULL,
	[estado] [varchar](10) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[codigo] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CarritoCompra]    Script Date: 01/04/2025 17:46:04 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CarritoCompra](
	[codigo] [int] IDENTITY(1,1) NOT NULL,
	[fechaCompra] [date] NOT NULL,
	[costoTotal] [float] NOT NULL,
	[FK_codigoPedido] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[codigo] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Catalogo]    Script Date: 01/04/2025 17:46:05 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Catalogo](
	[codigo] [int] IDENTITY(1,1) NOT NULL,
	[nombre] [varchar](50) NOT NULL,
	[fechainicio] [date] NOT NULL,
	[fechaFin] [date] NOT NULL,
	[estado] [varchar](30) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[codigo] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Ciudad]    Script Date: 01/04/2025 17:46:05 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Ciudad](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[nombre] [varchar](100) NOT NULL,
	[estado] [varchar](20) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Cliente]    Script Date: 01/04/2025 17:46:05 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Cliente](
	[codigo] [int] IDENTITY(1,1) NOT NULL,
	[nombre] [varchar](50) NOT NULL,
	[apellidoPaterno] [varchar](50) NOT NULL,
	[apellidoMaterno] [varchar](50) NOT NULL,
	[telefono] [varchar](20) NULL,
	[direccion] [varchar](200) NULL,
	[correo] [varchar](80) NULL,
	[estado] [varchar](20) NULL,
PRIMARY KEY CLUSTERED 
(
	[codigo] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Compra]    Script Date: 01/04/2025 17:46:05 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Compra](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[detalle] [varchar](200) NOT NULL,
	[cantidad] [int] NOT NULL,
	[precioTotal] [float] NOT NULL,
	[estado] [varchar](30) NOT NULL,
	[FK_codigoProducto] [int] NULL,
	[FK_codigoProveedor] [int] NULL,
	[fecha] [date] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Delivery]    Script Date: 01/04/2025 17:46:05 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Delivery](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[nombre] [varchar](100) NOT NULL,
	[estado] [varchar](20) NOT NULL,
	[fk_codigopedido] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[DetalleCarrito]    Script Date: 01/04/2025 17:46:05 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DetalleCarrito](
	[codigo] [int] IDENTITY(1,1) NOT NULL,
	[cantidad] [int] NOT NULL,
	[subtotal] [float] NOT NULL,
	[FK_codigoCarrito] [int] NULL,
	[FK_codigoProducto] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[codigo] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[DetalleCatalogo]    Script Date: 01/04/2025 17:46:05 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DetalleCatalogo](
	[codigo] [int] IDENTITY(1,1) NOT NULL,
	[FK_codigoProductoFijo] [int] NULL,
	[FK_codigoCatalogo] [int] NULL,
	[descripcion] [varchar](200) NULL,
PRIMARY KEY CLUSTERED 
(
	[codigo] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[DetalleCompra]    Script Date: 01/04/2025 17:46:05 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DetalleCompra](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[cantidad] [int] NOT NULL,
	[precioUnitario] [decimal](10, 2) NOT NULL,
	[precioTotal] [decimal](10, 2) NOT NULL,
	[idOrdenCompra] [int] NULL,
	[idMaterial] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[DetalleProductoPersonalizado]    Script Date: 01/04/2025 17:46:05 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DetalleProductoPersonalizado](
	[codigo] [int] IDENTITY(1,1) NOT NULL,
	[subTotal] [int] NOT NULL,
	[FK_codigoProductoPersonalizado] [int] NULL,
	[FK_codigoAccesorio] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[codigo] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Material]    Script Date: 01/04/2025 17:46:05 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Material](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[nombre] [varchar](100) NOT NULL,
	[tipo] [varchar](50) NOT NULL,
	[stock] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[OrdenCompra]    Script Date: 01/04/2025 17:46:05 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[OrdenCompra](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[fechaRegistro] [date] NOT NULL,
	[detalle] [varchar](200) NULL,
	[estado] [varchar](30) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Pedido]    Script Date: 01/04/2025 17:46:05 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Pedido](
	[codigo] [int] IDENTITY(1,1) NOT NULL,
	[fechaPedido] [date] NOT NULL,
	[costoTotal] [float] NOT NULL,
	[estado] [varchar](30) NOT NULL,
	[fechaPago] [date] NOT NULL,
	[tipoEnvio] [varchar](30) NOT NULL,
	[FK_codigoCliente] [int] NULL,
	[nropedido] [int] NOT NULL,
	[detalles] [varchar](200) NULL,
PRIMARY KEY CLUSTERED 
(
	[codigo] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Perfil]    Script Date: 01/04/2025 17:46:05 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Perfil](
	[codigo] [int] IDENTITY(1,1) NOT NULL,
	[nombre] [varchar](50) NOT NULL,
	[estado] [varchar](10) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[codigo] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Producto]    Script Date: 01/04/2025 17:46:05 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Producto](
	[codigo] [int] IDENTITY(1,1) NOT NULL,
	[nombre] [varchar](30) NOT NULL,
	[estado] [varchar](30) NOT NULL,
	[precio] [float] NOT NULL,
	[descripcion] [varchar](200) NULL,
PRIMARY KEY CLUSTERED 
(
	[codigo] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[producto_personalizado]    Script Date: 01/04/2025 17:46:05 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[producto_personalizado](
	[codigo] [int] IDENTITY(1,1) NOT NULL,
	[descripcion] [varchar](200) NOT NULL,
	[FK_codigoProducto] [int] NULL,
	[nombre] [varchar](100) NOT NULL,
	[FK_codigo_Catalogo] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[codigo] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ProductoFijo]    Script Date: 01/04/2025 17:46:05 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ProductoFijo](
	[codigo] [int] IDENTITY(1,1) NOT NULL,
	[nombre] [varchar](50) NOT NULL,
	[categoria] [varchar](50) NOT NULL,
	[FK_codigoProducto] [int] NULL,
	[FK_codigoCatalogo] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[codigo] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Proveedor]    Script Date: 01/04/2025 17:46:05 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Proveedor](
	[codigo] [int] IDENTITY(1,1) NOT NULL,
	[nombre] [varchar](100) NOT NULL,
	[direccion] [varchar](200) NOT NULL,
	[telefono] [varchar](20) NOT NULL,
	[correo] [varchar](80) NOT NULL,
	[idCiudad] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[codigo] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Usuario]    Script Date: 01/04/2025 17:46:05 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Usuario](
	[codigo] [int] IDENTITY(1,1) NOT NULL,
	[correo] [varchar](50) NOT NULL,
	[password] [varchar](100) NOT NULL,
	[estado] [varchar](10) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[codigo] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[UsuarioxPerfil]    Script Date: 01/04/2025 17:46:05 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[UsuarioxPerfil](
	[codigo] [int] IDENTITY(1,1) NOT NULL,
	[perfil] [varchar](50) NOT NULL,
	[FK_codigoUsuario] [int] NULL,
	[FK_codigoPerfil] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[codigo] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[Catalogo] ON 

INSERT [dbo].[Catalogo] ([codigo], [nombre], [fechainicio], [fechaFin], [estado]) VALUES (2, N'Prueba nombre catalogo', CAST(N'2025-01-09' AS Date), CAST(N'2026-01-24' AS Date), N'activo')
INSERT [dbo].[Catalogo] ([codigo], [nombre], [fechainicio], [fechaFin], [estado]) VALUES (3, N'Prueba nombre21312', CAST(N'2025-04-04' AS Date), CAST(N'2025-05-02' AS Date), N'activo')
INSERT [dbo].[Catalogo] ([codigo], [nombre], [fechainicio], [fechaFin], [estado]) VALUES (4, N'111111', CAST(N'2025-04-01' AS Date), CAST(N'2025-05-10' AS Date), N'activo')
SET IDENTITY_INSERT [dbo].[Catalogo] OFF
GO
SET IDENTITY_INSERT [dbo].[Cliente] ON 

INSERT [dbo].[Cliente] ([codigo], [nombre], [apellidoPaterno], [apellidoMaterno], [telefono], [direccion], [correo], [estado]) VALUES (1, N'andy12312', N'gonzales', N'castillo', N'960936934', N'mzh', N'andy@gmail.com', NULL)
INSERT [dbo].[Cliente] ([codigo], [nombre], [apellidoPaterno], [apellidoMaterno], [telefono], [direccion], [correo], [estado]) VALUES (3, N'12', N'123', N'123', N'123', N'123', N'123@gmail.com', NULL)
SET IDENTITY_INSERT [dbo].[Cliente] OFF
GO
SET IDENTITY_INSERT [dbo].[Pedido] ON 

INSERT [dbo].[Pedido] ([codigo], [fechaPedido], [costoTotal], [estado], [fechaPago], [tipoEnvio], [FK_codigoCliente], [nropedido], [detalles]) VALUES (1, CAST(N'2025-03-31' AS Date), 123, N'Activo', CAST(N'2025-04-09' AS Date), N'Delivery Express', 1, 1, N'aaaaaa')
INSERT [dbo].[Pedido] ([codigo], [fechaPedido], [costoTotal], [estado], [fechaPago], [tipoEnvio], [FK_codigoCliente], [nropedido], [detalles]) VALUES (3, CAST(N'2025-04-03' AS Date), 1231231, N'Activo', CAST(N'2025-05-01' AS Date), N'Delivery Express', 3, 3, N'12312')
SET IDENTITY_INSERT [dbo].[Pedido] OFF
GO
SET IDENTITY_INSERT [dbo].[Producto] ON 

INSERT [dbo].[Producto] ([codigo], [nombre], [estado], [precio], [descripcion]) VALUES (1, N'Prueba nombre producto', N'Activo', 100.09999847412109, NULL)
SET IDENTITY_INSERT [dbo].[Producto] OFF
GO
SET IDENTITY_INSERT [dbo].[ProductoFijo] ON 

INSERT [dbo].[ProductoFijo] ([codigo], [nombre], [categoria], [FK_codigoProducto], [FK_codigoCatalogo]) VALUES (1, N'Prueba nombre producto fijo', N'1', 1, 2)
SET IDENTITY_INSERT [dbo].[ProductoFijo] OFF
GO
SET IDENTITY_INSERT [dbo].[Usuario] ON 

INSERT [dbo].[Usuario] ([codigo], [correo], [password], [estado]) VALUES (1, N'andy@gmail.com', N'111', N'Activo')
SET IDENTITY_INSERT [dbo].[Usuario] OFF
GO
ALTER TABLE [dbo].[CarritoCompra]  WITH CHECK ADD FOREIGN KEY([FK_codigoPedido])
REFERENCES [dbo].[Pedido] ([codigo])
GO
ALTER TABLE [dbo].[Compra]  WITH CHECK ADD FOREIGN KEY([FK_codigoProducto])
REFERENCES [dbo].[Producto] ([codigo])
GO
ALTER TABLE [dbo].[Compra]  WITH CHECK ADD FOREIGN KEY([FK_codigoProveedor])
REFERENCES [dbo].[Proveedor] ([codigo])
GO
ALTER TABLE [dbo].[Compra]  WITH CHECK ADD  CONSTRAINT [FK_Compra_Proveedor] FOREIGN KEY([FK_codigoProveedor])
REFERENCES [dbo].[Proveedor] ([codigo])
GO
ALTER TABLE [dbo].[Compra] CHECK CONSTRAINT [FK_Compra_Proveedor]
GO
ALTER TABLE [dbo].[Delivery]  WITH CHECK ADD FOREIGN KEY([fk_codigopedido])
REFERENCES [dbo].[Pedido] ([codigo])
GO
ALTER TABLE [dbo].[DetalleCarrito]  WITH CHECK ADD FOREIGN KEY([FK_codigoCarrito])
REFERENCES [dbo].[CarritoCompra] ([codigo])
GO
ALTER TABLE [dbo].[DetalleCarrito]  WITH CHECK ADD FOREIGN KEY([FK_codigoProducto])
REFERENCES [dbo].[Producto] ([codigo])
GO
ALTER TABLE [dbo].[DetalleCatalogo]  WITH CHECK ADD FOREIGN KEY([FK_codigoProductoFijo])
REFERENCES [dbo].[ProductoFijo] ([codigo])
GO
ALTER TABLE [dbo].[DetalleCatalogo]  WITH CHECK ADD FOREIGN KEY([FK_codigoCatalogo])
REFERENCES [dbo].[Catalogo] ([codigo])
GO
ALTER TABLE [dbo].[DetalleCompra]  WITH CHECK ADD FOREIGN KEY([idMaterial])
REFERENCES [dbo].[Material] ([id])
GO
ALTER TABLE [dbo].[DetalleCompra]  WITH CHECK ADD FOREIGN KEY([idOrdenCompra])
REFERENCES [dbo].[OrdenCompra] ([id])
GO
ALTER TABLE [dbo].[DetalleProductoPersonalizado]  WITH CHECK ADD FOREIGN KEY([FK_codigoProductoPersonalizado])
REFERENCES [dbo].[producto_personalizado] ([codigo])
GO
ALTER TABLE [dbo].[DetalleProductoPersonalizado]  WITH CHECK ADD FOREIGN KEY([FK_codigoAccesorio])
REFERENCES [dbo].[Accesorio] ([codigo])
GO
ALTER TABLE [dbo].[Pedido]  WITH CHECK ADD FOREIGN KEY([FK_codigoCliente])
REFERENCES [dbo].[Cliente] ([codigo])
GO
ALTER TABLE [dbo].[producto_personalizado]  WITH CHECK ADD FOREIGN KEY([FK_codigoProducto])
REFERENCES [dbo].[Producto] ([codigo])
GO
ALTER TABLE [dbo].[producto_personalizado]  WITH CHECK ADD  CONSTRAINT [FK_ProductoPers_Catalogo] FOREIGN KEY([FK_codigo_Catalogo])
REFERENCES [dbo].[Catalogo] ([codigo])
GO
ALTER TABLE [dbo].[producto_personalizado] CHECK CONSTRAINT [FK_ProductoPers_Catalogo]
GO
ALTER TABLE [dbo].[ProductoFijo]  WITH CHECK ADD FOREIGN KEY([FK_codigoProducto])
REFERENCES [dbo].[Producto] ([codigo])
GO
ALTER TABLE [dbo].[ProductoFijo]  WITH CHECK ADD  CONSTRAINT [FK_ProductoFijo_Catalogo] FOREIGN KEY([FK_codigoCatalogo])
REFERENCES [dbo].[Catalogo] ([codigo])
GO
ALTER TABLE [dbo].[ProductoFijo] CHECK CONSTRAINT [FK_ProductoFijo_Catalogo]
GO
ALTER TABLE [dbo].[Proveedor]  WITH CHECK ADD  CONSTRAINT [FK_Proveedor_Ciudad] FOREIGN KEY([idCiudad])
REFERENCES [dbo].[Ciudad] ([id])
GO
ALTER TABLE [dbo].[Proveedor] CHECK CONSTRAINT [FK_Proveedor_Ciudad]
GO
ALTER TABLE [dbo].[UsuarioxPerfil]  WITH CHECK ADD FOREIGN KEY([FK_codigoUsuario])
REFERENCES [dbo].[Usuario] ([codigo])
GO
ALTER TABLE [dbo].[UsuarioxPerfil]  WITH CHECK ADD FOREIGN KEY([FK_codigoPerfil])
REFERENCES [dbo].[Perfil] ([codigo])
GO
/****** Object:  StoredProcedure [dbo].[SP_buscarPedidosxId]    Script Date: 01/04/2025 17:46:05 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[SP_buscarPedidosxId]
    @idBuscado INT
AS
BEGIN
    SELECT codigo, fechaPedido, costoTotal, estado, fechaPago, tipoEnvio, FK_codigoCliente
    FROM Pedido
    WHERE codigo = @idBuscado;
END;
GO
/****** Object:  StoredProcedure [dbo].[SP_registrarCarritoCompra]    Script Date: 01/04/2025 17:46:05 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[SP_registrarCarritoCompra]
    @fechaCompra DATE,
    @costoTotal FLOAT,
    @FK_codigoPedido INT
AS
BEGIN
    INSERT INTO CarritoCompra (fechaCompra, costoTotal, FK_codigoPedido)
    VALUES (@fechaCompra, @costoTotal, @FK_codigoPedido);
END;
GO
/****** Object:  StoredProcedure [dbo].[SP_registrarCliente]    Script Date: 01/04/2025 17:46:05 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

-- Procedimientos almacenados

CREATE PROCEDURE [dbo].[SP_registrarCliente]
    @nombre VARCHAR(50),
    @apellidoPaterno VARCHAR(50),
    @apellidoMaterno VARCHAR(50),
    @telefono VARCHAR(20),
    @direccion VARCHAR(200),
    @correo VARCHAR(80)
AS
BEGIN
    INSERT INTO Cliente (nombre, apellidoPaterno, apellidoMaterno, telefono, direccion, correo)
    VALUES (@nombre, @apellidoPaterno, @apellidoMaterno, @telefono, @direccion, @correo);
END;
GO
/****** Object:  StoredProcedure [dbo].[SP_registrarPedido]    Script Date: 01/04/2025 17:46:05 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[SP_registrarPedido]
    @fechaPedido DATE,
    @costoTotal FLOAT,
    @estado VARCHAR(30),
    @fechaPago DATE,
    @tipoEnvio VARCHAR(30),
    @FK_codigoCliente INT
AS
BEGIN
    INSERT INTO Pedido (fechaPedido, costoTotal, estado, fechaPago, tipoEnvio, FK_codigoCliente)
    VALUES (@fechaPedido, @costoTotal, @estado, @fechaPago, @tipoEnvio, @FK_codigoCliente);
END;
GO
/****** Object:  StoredProcedure [dbo].[SP_registrarProveedor]    Script Date: 01/04/2025 17:46:05 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[SP_registrarProveedor]
    @nombre VARCHAR(100),
    @direccion VARCHAR(200),
    @telefono VARCHAR(20),
    @correo VARCHAR(80)
AS
BEGIN
    INSERT INTO Proveedor (nombre, direccion, telefono, correo)
    VALUES (@nombre, @direccion, @telefono, @correo);
END;
GO
/****** Object:  StoredProcedure [dbo].[SP_registrarUsuario]    Script Date: 01/04/2025 17:46:05 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROCEDURE [dbo].[SP_registrarUsuario]
    @correo VARCHAR(50),
    @password VARCHAR(50),
    @estado VARCHAR(10)
AS
BEGIN
    INSERT INTO Usuario (correo, password, estado)
    VALUES (@correo, @password, @estado);
END;
GO
USE [master]
GO
ALTER DATABASE [SistemaParcial] SET  READ_WRITE 
GO
