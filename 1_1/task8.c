/*
 * Input file: 24 bit no whitespace bmp file
 *
 * BMP is Bitmap imagige file holding the image information in bits
 * -The structure of information must correspond byte wise
 *
 * Filter matrixes and their info is found in a quick internet search.
 *
 */
//TODO:
//~
//
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#pragma pack(push, 1)

typedef struct
{
	unsigned int Size;
	unsigned int Width;
	unsigned int Height;
	unsigned short biBitCount;
	unsigned short biPlanes;
    unsigned int biCompression;
    unsigned int biSizeImage;
    unsigned int biXPelsPerMeter;
    unsigned int biYPelsPerMeter;
    unsigned int biClrUsed;
    unsigned int biClrImportant;

}BitmapInfoHeader;

typedef struct
{
	unsigned short Type;
	unsigned int Size;
	unsigned short bfReserved1;
	unsigned short bfReserved2;
	unsigned int OffBits;
}BitmapFileHeader;

typedef struct
{
	unsigned char blue;
	unsigned char green;
	unsigned char red;
}RGB;

#pragma pack(pop)

typedef struct
{
	BitmapFileHeader file_header;
	BitmapInfoHeader info_header;
	char *platte;
	size_t platte_size;
	RGB *image_data;
}BitmapImage;

void read_image_file(char *input_file, BitmapImage *image)
{
	FILE *fp = fopen(input_file, "rb");
	//if !fp "can't open file"
	BitmapFileHeader *file_header = &image->file_header;
	fread(file_header, sizeof(BitmapFileHeader), 1, fp);
	//if file_header->type != 0x4D42 "file isn't bmp format"
	
	BitmapInfoHeader *info_header = &image->info_header;
	fread(info_header, sizeof(BitmapInfoHeader), 1, fp);
	//if info_header->BitCount != 24 && 32 "file is not 32 or 24 bits"
	
	fseek(fp, file_header->OffBits, SEEK_SET);

	RGB *image_data = (RGB *)malloc(info_header->Width * info_header->Height * sizeof(RGB));
	//if !image_data "Couldn't malloc image
	
	//platte
	size_t platteSize = file_header->OffBits - sizeof(BitmapFileHeader) - sizeof(BitmapInfoHeader);
	image->platte = NULL;
	image->platte_size = 0;
	
	if(platteSize > 0)
	{
		image->platte = (char *)malloc(platteSize);
		image->platte_size = platteSize;
		fread(image->platte, 1, platteSize, fp);
	}
	int k = 0;
	for (int i = 0; i < info_header->Height; i++)
		for(int j = 0; j < info_header->Width; j++)
			fread(image_data + k++, sizeof(RGB), 1, fp);

	image->image_data = image_data;
	fclose(fp);
}

void write_image_file(char *output_file, BitmapImage *image)
{
	FILE *fp = fopen(output_file, "wb");
	//if !fp "can't open output file"
	fwrite(&image->file_header, sizeof(BitmapFileHeader), 1, fp);
	fwrite(&image->info_header, sizeof(BitmapInfoHeader), 1, fp);
	if (image->platte)
		fwrite(&image->platte, image->platte_size, 1, fp);
	for (int i = 0; i < image->info_header.Width * image->info_header.Height; i++)
		fwrite(&image->image_data[i], sizeof(RGB), 1, fp);
	fclose(fp);
}

void apply_matrix(RGB *img, int width, int height, int *matrix, int dim)
{
	int i, j, k, l, r, g, b, gap, weight;
	RGB *t;

	t = (RGB *)malloc(width * height * sizeof(RGB));
	memcpy(t, img, width * height * sizeof(RGB));

	gap = (dim - 1) / 2;
	weight = 0;
	for (i = -gap; i <= gap; i++)
		for(j = -gap; j <= gap; j++)
			weight += matrix[(gap + i) * dim + gap + j];

	for (i = gap; i < height - gap; i++)
	{
		for(j = gap; j < width - gap; j++)
		{
			r = g = b = 0;
			for (k = -gap; k <= gap; k++)
			{
				for(l = -gap; l <= gap; l++)
				{
					r += t[(i + k) * width + j + l].red * matrix[(gap + k) * dim + gap + l];
					g += t[(i + k) * width + j + l].green * matrix[(gap + k) * dim + gap + l];
					b += t[(i + k) * width + j + l].blue * matrix[(gap + k) * dim + gap + l];
				}
			}
			img[i * width + j].red = r/weight;
			img[i * width + j].green = g/weight;
			img[i * width + j].blue = b/weight;
		}
	}
	free(t);
}

void f_average(RGB *img, int width, int height)
{
	int matrix[] = 
	{
		1,1,1,
		1,1,1,
		1,1,1
	};
	apply_matrix(img, width, height, matrix, 3);
}

void f_gauss(RGB *img, int width, int height)
{
	int matrix[] = 
	{
		1,4,6,4,1,
		4,16,24,16,4,
		6,24,36,24,6,
		4,16,24,16,4,
		1,4,6,4,1,	
	};
	apply_matrix(img, width, height, matrix, 5);
}

void apply_sobel(RGB *img, int width, int height, int *matrix)
{
	int i, j, k, l, r, g, b;
	RGB *t;

	t = (RGB *)malloc(width * height * sizeof(RGB));
	memcpy(t, img, width * height * sizeof(RGB));

	for (i = 1; i < height - 1; i++)
	{
		for(j = 1; j < width - 1; j++)
		{
			r = g = b = 0;
			for (k = -1; k <= 1; k++)
			{
				for (l = -1; l <= 1; l++)
				{
					r += t[(i + k) * width + j + l].red * matrix[(1 + k) * 3 + 1 + l];
					g += t[(i + k) * width + j + l].green * matrix[(1 + k) * 3 + 1 + l];
					b += t[(i + k) * width + j + l].blue * matrix[(1 + k) * 3 + 1 + l];
				}
			}
			if (r < 0) r = 0;
			if (r > 255) r = 255;
			if (g < 0) g = 0;
			if (g > 255) g = 255;
			if (b < 0) b = 0;
			if (b > 255) b = 255;
			
			img[i * width + j].red = r;
			img[i * width + j].green = g;
			img[i * width + j].blue = b;
		}
	}
	free(t);
}

void f_sobelX(RGB *img, int width, int height)
{
	int matrix[] = 
	{
		1,2,1,
		0,0,0,
		-1,-2,-1
	};
	apply_sobel(img, width, height, matrix);
}
void f_sobelY(RGB *img, int width, int height)
{
	int matrix[] = 
	{
		-1,0,1,
		-2,0,2,
		-1,0,1
	};
	apply_sobel(img, width, height, matrix);
}

void f_gray(RGB *img, int width, int height)
{
	int val;
	for(int i = 0; i < height; i++)
	{
		for (int j = 0; j < width; j++)
		{
			val = (img[i * width + j].red + img[i * width + j].green + img[i * width + j].blue) / 3;
			img[i * width + j].red = val;
			img[i * width + j].green = val;
			img[i * width + j].blue = val;
		}
	}
}	

int main(int argc, char *argv[])
{
	if (argc != 4) 
	{
		printf("out FILTER_NAME INPUT_BMP OUTPUT_BMP\n");
		printf("Filters:\n average, gauss, sobelX, sobelY, gray\n");
		return -1;
	}

	BitmapImage image;
	read_image_file(argv[2], &image);
	int width = image.info_header.Width;
	int height = image.info_header.Height;

	if(strcmp(argv[1], "average") == 0)
		f_average(image.image_data, width, height);
	if(strcmp(argv[1], "gauss") == 0)
		f_gauss(image.image_data, width, height);
	if(strcmp(argv[1], "sobelX") == 0)
		f_sobelX(image.image_data, width, height);
	if(strcmp(argv[1], "sobelY") == 0)
		f_sobelY(image.image_data, width, height);
	if(strcmp(argv[1], "gray") == 0)
		f_gray(image.image_data, width, height);
	write_image_file(argv[3], &image);
	free(image.image_data);
	return 0;
}
